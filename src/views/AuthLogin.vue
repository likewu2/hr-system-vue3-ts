<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useHRStore } from '../stores/useHRStore'

const username = ref('')
const password = ref('')
const error = ref('')
const loading = ref(false)

const router = useRouter()
const store = useHRStore()

const login = async () => {
  loading.value = true
  // 模拟登录 API
  if (username.value && password.value) {
    // 假设后端返回用户角色
    const mockUser = {
      id: 1,
      name: username.value,
      role: username.value === 'admin' ? 'admin' : 'user',
      token: 'mock-jwt-token-123'
    }
    store.login(mockUser)
    router.push('/')
  } else {
    error.value = '请输入用户名和密码'
  }
  loading.value = false
}
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center px-4">
    <div class="bg-white p-8 rounded-xl shadow-lg w-full max-w-md">
      <div class="text-center mb-8">
        <h1 class="text-2xl font-bold text-gray-800">HR 管理系统</h1>
        <p class="text-gray-500 mt-2">泛微OA风格原型</p>
      </div>

      <form @submit.prevent="login">
        <div class="mb-6">
          <label class="block text-sm font-medium text-gray-700 mb-2">用户名</label>
          <input v-model="username" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none" placeholder="admin 或 user" />
        </div>

        <div class="mb-6">
          <label class="block text-sm font-medium text-gray-700 mb-2">密码</label>
          <input v-model="password" type="password" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none" />
        </div>

        <div v-if="error" class="text-red-500 text-sm mb-4">{{ error }}</div>

        <button type="submit" :disabled="loading" class="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 disabled:opacity-70">
          {{ loading ? '登录中...' : '登录' }}
        </button>
      </form>
    </div>
  </div>
</template>