// Vue 3 + TypeScript 示例
import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 10000
})

// 获取员工列表
const res = await api.get('/employees')
console.log(res.data.data)