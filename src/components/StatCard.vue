<script setup>
defineProps({
  title: String,
  value: [String, Number],
  color: String,
  icon: String
})

const colorClasses = {
  blue: 'bg-blue-50 border-blue-200 text-blue-700',
  green: 'bg-green-50 border-green-200 text-green-700',
  orange: 'bg-orange-50 border-orange-200 text-orange-700',
  purple: 'bg-purple-50 border-purple-200 text-purple-700'
}
</script>

<template>
  <div class="bg-white p-6 rounded-lg shadow border-l-4" :class="colorClasses[color]">
    <div class="text-sm text-gray-500">{{ title }}</div>
    <div class="text-3xl font-bold mt-1">{{ value }}</div>
    <div class="text-lg mt-1">{{ icon }}</div>
  </div>
</template>