export interface User {
  id: number
  name: string
  email: string
  role: 'admin' | 'user' | 'hr'
  department: string
  position: string
  avatar?: string
}

export interface OrgNode {
  id: string
  name: string
  position: string
  department: string
  children?: OrgNode[]
}

export interface AttendanceRecord {
  date: string
  status: 'present' | 'absent' | 'late' | 'leave'
  hours: number
}

export interface OnboardingItem {
  id: number
  name: string
  dept: string
  position: string
  entryDate: string
  status: '待入职' | '已入职' | '已取消'
}

export interface Contract {
  id: number
  employeeName: string
  type: '试用期' | '正式' | '项目'
  startDate: string
  endDate: string
  status: '待签署' | '已签署' | '已过期'
}

export interface ApprovalItem {
  id: number
  title: string
  requester: string
  type: '请假' | '报销' | '调岗' | '合同'
  date: string
  amount?: number
}

export interface AuthTokens {
  token: string
  refreshToken: string
  user: User
}