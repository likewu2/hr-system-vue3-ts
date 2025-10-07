<script setup>
import { ref, onMounted } from 'vue'
import { api } from '@/api'

const approvals = ref([])

const load = () => api.getApprovals().then(data => approvals.value = data)
const approve = async (id) => {
  await api.approveItem(id)
  load()
}
const reject = async (id) => {
  await api.rejectItem(id)
  load()
}

onMounted(load)
</script>

<template>
  <div class="p-6">
    <h1 class="text-xl font-bold mb-6">审批中心</h1>
    <div class="space-y-4">
      <div v-for="a in approvals" :key="a.id" class="bg-white p-4 rounded-lg shadow">
        <div class="flex justify-between">
          <div>
            <div class="font-medium">{{ a.title }}</div>
            <div class="text-sm text-gray-500">申请人：{{ a.requester }} | {{ a.type }}</div>
          </div>
          <div class="text-right">
            <div class="text-sm text-gray-500">{{ a.date }}</div>
            <div class="flex gap-2 mt-2">
              <button @click="approve(a.id)" class="bg-green-600 text-white px-3 py-1 rounded text-sm">同意</button>
              <button @click="reject(a.id)" class="bg-red-600 text-white px-3 py-1 rounded text-sm">拒绝</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>