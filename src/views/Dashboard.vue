<script setup>
import StatCard from '../components/StatCard.vue'
import { ref, onMounted } from 'vue'
import { employees } from '../data/employees'

const stats = ref({
  total: 0,
  newHires: 0,
  pending: 0,
  attendance: '0%'
})

onMounted(() => {
  // 模拟数据加载
  stats.value = {
    total: employees.length,
    newHires: employees.filter(e => e.入职 >= '2024-09-01').length,
    pending: 2,
    attendance: '98.5%'
  }
})
</script>

<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <h1 class="text-2xl font-bold text-gray-800 mb-6">人力资源管理系统</h1>

    <!-- 统计卡片 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <StatCard title="员工总数" :value="stats.total" color="blue" icon="👥" />
      <StatCard title="本月入职" :value="stats.newHires" color="green" icon="🎉" />
      <StatCard title="待审批" :value="stats.pending" color="orange" icon="📝" />
      <StatCard title="出勤率" :value="stats.attendance" color="purple" icon="✅" />
    </div>

    <!-- 快捷入口 -->
    <div class="grid grid-cols-2 md:grid-cols-4 gap-4">
      <router-link to="/employees" class="bg-white p-6 rounded-lg shadow text-center hover:shadow-lg transition">
        <div class="text-4xl mb-2">👥</div>
        <div class="font-medium">员工管理</div>
      </router-link>

      <router-link to="/org-chart" class="bg-white p-6 rounded-lg shadow text-center hover:shadow-lg transition">
        <div class="text-4xl mb-2">🏢</div>
        <div class="font-medium">组织架构</div>
      </router-link>

      <router-link to="/attendance" class="bg-white p-6 rounded-lg shadow text-center hover:shadow-lg transition">
        <div class="text-4xl mb-2">📅</div>
        <div class="font-medium">考勤管理</div>
      </router-link>

      <div class="bg-white p-6 rounded-lg shadow text-center opacity-70 cursor-not-allowed">
        <div class="text-4xl mb-2">💰</div>
        <div class="font-medium">薪资管理</div>
      </div>
    </div>
  </div>
</template>