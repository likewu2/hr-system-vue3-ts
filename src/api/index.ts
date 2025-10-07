import type { User, OrgNode, AttendanceRecord, OnboardingItem, Contract, ApprovalItem, AuthTokens } from '@/types'

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080/api'

export const request = async <T>(url: string, options: RequestInit = {}): Promise<T> => {
  const store = useHRStore()
  const headers = {
    'Content-Type': 'application/json',
    ...options.headers
  }

  if (store.token) {
    headers.Authorization = `Bearer ${store.token}`
  }

  const res = await fetch(API_BASE + url, { ...options, headers })

  if (res.status === 401) {
    if (store.isRefreshing) {
      return new Promise<T>((resolve, reject) => {
        store.failedQueue.push({ resolve: () => resolve(request<T>(url, options)), reject })
      })
    } else {
      const refreshed = await store.refreshToken()
      if (refreshed) {
        return request<T>(url, options)
      } else {
        throw new Error('登录已过期')
      }
    }
  }

  if (!res.ok) throw new Error(`API Error: ${res.statusText}`)
  return await res.json() as T
}

export const api = {
  login: (creds: { email: string; password: string }): Promise<AuthTokens> =>
    request('/auth/login', { method: 'POST', body: JSON.stringify(creds) }),
  getUsers: (): Promise<User[]> => request('/users'),
  getOrgChart: (): Promise<OrgNode> => request('/org/chart'),
  getAttendance: (): Promise<AttendanceRecord[]> => request('/attendance/monthly'),
  
  getOnboardingList: (): Promise<OnboardingItem[]> => request('/onboarding'),
  createOnboarding: (data) => request('/onboarding', { method: 'POST', body: JSON.stringify(data) }),

  getContracts: (): Promise<Contract[]> => request('/contracts'),
  signContract: (id) => request(`/contracts/${id}/sign`, { method: 'POST' }),

  getApprovals: (): Promise<ApprovalItem[]> => request('/approvals'),
  approveItem: (id) => request(`/approvals/${id}/approve`, { method: 'POST' }),
  rejectItem: (id) => request(`/approvals/${id}/reject`, { method: 'POST' })
}

import { useHRStore } from '@/stores/useHRStore'