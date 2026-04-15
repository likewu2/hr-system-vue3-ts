<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const warehouses = ref([])
const loading = ref(true)

const fetchWarehouses = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/warehouses')
    const data = await response.json()
    if (data.success) {
      warehouses.value = data.data
    }
  } catch (error) {
    console.error('获取档案库房失败:', error)
  } finally {
    loading.value = false
  }
}

const goToWarehouseDetail = (id) => {
  router.push(`/warehouse/${id}`)
}

onMounted(() => {
  fetchWarehouses()
})
</script>

<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <h1 class="text-2xl font-bold text-gray-800 mb-6">档案库房管理</h1>

    <div v-if="loading" class="flex justify-center items-center h-64">
      <div class="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-blue-500"></div>
    </div>

    <div v-else class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
      <div 
        v-for="warehouse in warehouses" 
        :key="warehouse.id"
        class="bg-white rounded-lg shadow p-6 hover:shadow-lg transition cursor-pointer"
        @click="goToWarehouseDetail(warehouse.id)"
      >
        <div class="flex justify-between items-start mb-4">
          <h2 class="text-xl font-semibold text-gray-800">{{ warehouse.name }}</h2>
          <span class="bg-blue-100 text-blue-800 text-xs font-medium px-2.5 py-0.5 rounded">
            正常
          </span>
        </div>
        <div class="space-y-2 text-sm text-gray-600">
          <div><span class="font-medium">位置:</span> {{ warehouse.location }}</div>
          <div><span class="font-medium">描述:</span> {{ warehouse.description || '无' }}</div>
          <div><span class="font-medium">容量:</span> {{ warehouse.capacity || 0 }} 平方米</div>
        </div>
        <div class="mt-4 pt-4 border-t border-gray-100">
          <div class="flex justify-between text-sm text-gray-500">
            <span>设备数量: 8</span>
            <span>传感器: 12</span>
          </div>
        </div>
      </div>
    </div>

    <div v-if="!loading && warehouses.length === 0" class="text-center py-12">
      <div class="text-6xl mb-4">📦</div>
      <h3 class="text-lg font-medium text-gray-600">暂无档案库房</h3>
      <p class="text-gray-500 mt-2">请添加档案库房开始监控</p>
    </div>
  </div>
</template>