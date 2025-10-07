const API_BASE = 'http://localhost:8080/api'

export const login = async (credentials) => {
  const res = await fetch(`${API_BASE}/users/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(credentials)
  })
  return await res.json()
}

export const getUsers = async () => {
  const token = localStorage.getItem('hr_token')
  const res = await fetch(`${API_BASE}/users`, {
    headers: { 'Authorization': `Bearer ${token}` }
  })
  return await res.json()
}