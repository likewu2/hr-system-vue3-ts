<script setup>
import { ref, onMounted } from 'vue'
import { api } from '@/api'

const list = ref([])
const showForm = ref(false)
const newEntry = ref({ name: '', dept: '', position: '',入职: '' })

const load = () => api.getOnboardingList().then(data => list.value = data)
const add = async () => {
  await api.createOnboarding(newEntry.value)
  showForm.value = false
  newEntry.value = { name: '', dept: '', position: '',入职: '' }
  load()
}

onMounted(load)
</script>

<template>
  <div class="p-6">
    <div class="flex justify-between items-center mb-6">
      <h1 class="text-xl font-bold">入职管理</h1>
      <button @click="showForm = true" class="bg-green-600 text-white px-4 py-2 rounded-lg">+ 新增入职</button>
    </div>

    <!-- 入职列表 -->
    <div class="bg-white rounded-lg shadow overflow-hidden">
      <table class="min-w-full">
        <thead class="bg-gray-50">
          <tr>
            <th>姓名</th>
            <th>部门</th>
            <th>职位</th>
            <th>入职日期</th>
            <th>状态</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in list" :key="item.id">
            <td>{{ item.name }}</td>
            <td>{{ item.dept }}</td>
            <td>{{ item.position }}</td>
            <td>{{ item.入职 }}</td>
            <td><span class="px-2 py-1 bg-blue-100 text-blue-800 rounded text-sm">{{ item.status }}</span></td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 弹窗表单 -->
    <div v-if="showForm" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div class="bg-white p-6 rounded-lg w-96">
        <h3 class="text-lg font-bold mb-4">新增入职</h3>
        <input v-model="newEntry.name" placeholder="姓名" class="w-full border p-2 mb-2 rounded" />
        <input v-model="newEntry.dept" placeholder="部门" class="w-full border p-2 mb-2 rounded" />
        <input v-model="newEntry.position" placeholder="职位" class="w-full border p-2 mb-2 rounded" />
        <input v-model="newEntry.入职" type="date" class="w-full border p-2 mb-4 rounded" />
        <div class="flex justify-end">
          <button @click="showForm = false" class="mr-2 px-4 py-2 text-gray-600">取消</button>
          <button @click="add" class="bg-blue-600 text-white px-4 py-2 rounded">提交</button>
        </div>
      </div>
    </div>
  </div>
</template>