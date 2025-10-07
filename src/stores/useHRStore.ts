import { defineStore } from 'pinia'
import type { User, AuthTokens } from '@/types'

export const useHRStore = defineStore('hr', {
  state: () => ({
    user: null as User | null,
    token: null as string | null,
    refreshToken: null as string | null,
    isRefreshing: false,
    failedQueue: [] as Array<{
      resolve: (token: string) => void
      reject: (error: Error) => void
    }>
  }),
  getters: {
    isAdmin: (state): boolean => state.user?.role === 'admin'
  },
  actions: {
    setTokens(tokens: AuthTokens): void {
      this.token = tokens.token
      this.refreshToken = tokens.refreshToken
      this.user = tokens.user
      localStorage.setItem('hr_token', tokens.token)
      localStorage.setItem('hr_refresh_token', tokens.refreshToken)
    },
    async refreshToken(): Promise<boolean> {
      if (this.isRefreshing) return false
      this.isRefreshing = true
      try {
        const res = await fetch('http://localhost:8080/api/auth/refresh', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ refreshToken: this.refreshToken })
        })
        if (res.ok) {
          const tokens: AuthTokens = await res.json()
          this.setTokens(tokens)
          this.processQueue(null, tokens.token)
          return true
        } else {
          this.logout()
          return false
        }
      } catch (err) {
        this.logout()
        return false
      } finally {
        this.isRefreshing = false
      }
    },
    processQueue(error: Error | null, token: string | null = null): void {
      this.failedQueue.forEach(prom => {
        if (error) {
          prom.reject(error)
        } else {
          prom.resolve(token)
        }
      })
      this.failedQueue = []
    },
    logout(): void {
      this.user = null
      this.token = null
      this.refreshToken = null
      localStorage.removeItem('hr_token')
      localStorage.removeItem('hr_refresh_token')
    }
  }
})