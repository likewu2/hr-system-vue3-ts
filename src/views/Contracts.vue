<script setup>
import { ref, onMounted } from 'vue'
import { api } from '@/api'

const contracts = ref([])

const load = () => api.getContracts().then(data => contracts.value = data)
const sign = async (id) => {
  await api.signContract(id)
  load()
}

onMounted(load)
</script>

<template>
  <div class="p-6">
    <h1 class="text-xl font-bold mb-6">合同管理</h1>
    <div class="space-y-4">
      <div v-for="c in contracts" :key="c.id" class="bg-white p-4 rounded-lg shadow flex justify-between items-center">
        <div>
          <div class="font-medium">{{ c.employeeName }} - {{ c.type }}</div>
          <div class="text-sm text-gray-500">有效期：{{ c.startDate }} 至 {{ c.endDate }}</div>
        </div>
        <div>
          <span :class="{
            'bg-green-100 text-green-800': c.status === '已签署',
            'bg-yellow-100 text-yellow-800': c.status === '待签署'
          }" class="px-3 py-1 rounded-full text-sm">{{ c.status }}</span>
          <button v-if="c.status === '待签署'" @click="sign(c.id)" class="ml-4 bg-blue-600 text-white px-3 py-1 rounded text-sm">签署</button>
        </div>
      </div>
    </div>
  </div>
</template>