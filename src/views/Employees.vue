<script setup>
import { employees } from '../data/employees'
</script>

<template>
  <div class="p-6">
    <h1 class="text-xl font-bold mb-4">员工管理</h1>
    <div class="bg-white rounded-lg shadow overflow-hidden">
      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
          <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">姓名</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">部门</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">职位</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">入职日期</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">状态</th>
          </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="emp in employees" :key="emp.id">
            <td class="px-6 py-4">{{ emp.name }}</td>
            <td class="px-6 py-4">{{ emp.dept }}</td>
            <td class="px-6 py-4">{{ emp.position }}</td>
            <td class="px-6 py-4">{{ emp.入职 }}</td>
            <td class="px-6 py-4">
              <span class="px-2 py-1 text-xs rounded-full"
                :class="emp.status === '在职' ? 'bg-green-100 text-green-800' : 'bg-yellow-100 text-yellow-800'">
                {{ emp.status }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>