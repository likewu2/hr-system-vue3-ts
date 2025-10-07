<script setup>
import { onMounted, ref } from 'vue'
import { Chart } from 'chart.js/auto'

const chartRef = ref(null)

onMounted(() => {
  const ctx = chartRef.value.getContext('2d')
  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['1月', '2月', '3月', '4月', '5月', '6月'],
      datasets: [{
        label: '出勤率 (%)',
        data: [97.2, 98.5, 96.8, 99.1, 97.9, 98.3],
        backgroundColor: 'rgba(59, 130, 246, 0.6)',
        borderColor: 'rgba(59, 130, 246, 1)',
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { position: 'top' },
        title: { display: true, text: '2024年度月度出勤率' }
      }
    }
  })
})
</script>

<template>
  <div class="p-6">
    <h1 class="text-xl font-bold mb-4">考勤管理</h1>
    <div class="bg-white p-6 rounded-lg shadow">
      <canvas ref="chartRef"></canvas>
    </div>
  </div>
</template>