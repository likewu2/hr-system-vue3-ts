<script setup>
import { ref, onMounted, computed, onUnmounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const warehouseId = computed(() => route.params.id)

// 模拟数据
const warehouse = ref({
  id: 1,
  name: '档案库房',
  location: '第三层',
  description: '主要存储重要档案文件',
  capacity: 200
})

const airQuality = ref({
  tvoc: 2,
  pm25: 445,
  pm10: 388,
  co2: 946
})

const temperatureHumidity = ref({
  temperature: 10.8,
  humidity: 95
})

const equipmentStatus = ref({
  dehumidifier: {
    name: '恒温恒湿一体机',
    status: '运行中',
    humidity: 29.5,
    cooling: 40.2,
    heating: 27.4
  },
  airPurifier: {
    name: '空气净化',
    status: '运行中',
    efficiency: 85
  }
})

const devices = ref([
  { id: 1, name: '总开关', status: '开启', type: 'control' },
  { id: 2, name: '数字化开关', status: '开启', type: 'control' },
  { id: 3, name: '熏蒸开关', status: '关闭', type: 'control' },
  { id: 4, name: '熏蒸开关2', status: '关闭', type: 'control' },
  { id: 5, name: '空气净化开关', status: '开启', type: 'control' }
])

// 图表数据
const temperatureData = ref([10.5, 10.6, 10.7, 10.8, 10.7, 10.9, 10.8])
const humidityData = ref([94, 94.5, 95, 95.2, 95, 94.8, 95])
const timeLabels = ref(['10:00', '10:10', '10:20', '10:30', '10:40', '10:50', '11:00'])

let updateInterval = null

const fetchData = async () => {
  // 实际项目中这里会调用API获取数据
  console.log('获取档案库房详情:', warehouseId.value)
  // 模拟数据更新
  temperatureHumidity.value.temperature = (10 + Math.random() * 2).toFixed(1)
  temperatureHumidity.value.humidity = (90 + Math.random() * 10).toFixed(1)
  
  // 更新图表数据
  temperatureData.value.push(parseFloat(temperatureHumidity.value.temperature))
  humidityData.value.push(parseFloat(temperatureHumidity.value.humidity))
  timeLabels.value.push(new Date().toLocaleTimeString('zh-CN', { hour: '2-digit', minute: '2-digit' }))
  
  // 保持数据长度为7
  if (temperatureData.value.length > 7) {
    temperatureData.value.shift()
    humidityData.value.shift()
    timeLabels.value.shift()
  }
}

const toggleDevice = (device) => {
  device.status = device.status === '开启' ? '关闭' : '开启'
  console.log('控制设备:', device.name, '状态:', device.status)
}

onMounted(() => {
  fetchData()
  // 每10秒更新一次数据
  updateInterval = setInterval(fetchData, 10000)
})

onUnmounted(() => {
  if (updateInterval) {
    clearInterval(updateInterval)
  }
})
</script>

<template>
  <div class="min-h-screen bg-gradient-to-b from-blue-900 to-blue-950 text-white">
    <!-- 顶部标题 -->
    <div class="p-6 border-b border-blue-800">
      <div class="flex justify-between items-center">
        <h1 class="text-3xl font-bold">档案库房综合智能管控平台</h1>
        <div class="text-sm opacity-80">
          <span>{{ new Date().toLocaleString() }}</span>
        </div>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="p-6 grid grid-cols-12 gap-6">
      <!-- 左侧监控区域 -->
      <div class="col-span-12 lg:col-span-8 space-y-6">
        <!-- 空气质量监控 -->
        <div class="bg-blue-800 bg-opacity-50 rounded-lg p-4 transition-all duration-300 hover:shadow-lg hover:bg-opacity-60">
          <h2 class="text-xl font-semibold mb-4 flex items-center">
            <span class="text-yellow-400 mr-2">⭐</span>
            空气质量
          </h2>
          <div class="grid grid-cols-2 md:grid-cols-4 gap-4">
            <div class="bg-blue-900 bg-opacity-60 p-3 rounded transition-all duration-300 hover:bg-opacity-80 hover:scale-105">
              <div class="text-sm text-blue-300">TVOC</div>
              <div class="text-2xl font-bold">{{ airQuality.tvoc }} ppb</div>
            </div>
            <div class="bg-blue-900 bg-opacity-60 p-3 rounded transition-all duration-300 hover:bg-opacity-80 hover:scale-105">
              <div class="text-sm text-blue-300">PM2.5</div>
              <div class="text-2xl font-bold">{{ airQuality.pm25 }} μg/m³</div>
            </div>
            <div class="bg-blue-900 bg-opacity-60 p-3 rounded transition-all duration-300 hover:bg-opacity-80 hover:scale-105">
              <div class="text-sm text-blue-300">PM10</div>
              <div class="text-2xl font-bold">{{ airQuality.pm10 }} μg/m³</div>
            </div>
            <div class="bg-blue-900 bg-opacity-60 p-3 rounded transition-all duration-300 hover:bg-opacity-80 hover:scale-105">
              <div class="text-sm text-blue-300">CO2</div>
              <div class="text-2xl font-bold">{{ airQuality.co2 }} ppm</div>
            </div>
          </div>
        </div>

        <!-- 温湿度监控 -->
        <div class="bg-blue-800 bg-opacity-50 rounded-lg p-4 transition-all duration-300 hover:shadow-lg hover:bg-opacity-60">
          <h2 class="text-xl font-semibold mb-4 flex items-center">
            <span class="text-yellow-400 mr-2">🌡️</span>
            数字化温湿度
          </h2>
          <div class="grid grid-cols-2 gap-4 mb-4">
            <div class="bg-blue-900 bg-opacity-60 p-4 rounded text-center">
              <div class="text-sm text-blue-300">温度</div>
              <div class="text-3xl font-bold">{{ temperatureHumidity.temperature }} °C</div>
            </div>
            <div class="bg-blue-900 bg-opacity-60 p-4 rounded text-center">
              <div class="text-sm text-blue-300">湿度</div>
              <div class="text-3xl font-bold">{{ temperatureHumidity.humidity }} %</div>
            </div>
          </div>
          <!-- 温湿度趋势图 -->
          <div class="bg-blue-900 bg-opacity-60 p-4 rounded">
            <h3 class="text-sm font-medium mb-3">温湿度趋势</h3>
            <div class="h-48 relative">
              <!-- 温度趋势线 -->
              <div class="absolute inset-0">
                <svg class="w-full h-full">
                  <polyline 
                    :points="temperatureData.map((val, i) => `${(i / (temperatureData.length - 1)) * 100}% ${100 - ((val - 10) / 2) * 100}%`).join(' ')"
                    fill="none"
                    stroke="#ff6b6b"
                    stroke-width="2"
                  />
                </svg>
              </div>
              <!-- 湿度趋势线 -->
              <div class="absolute inset-0">
                <svg class="w-full h-full">
                  <polyline 
                    :points="humidityData.map((val, i) => `${(i / (humidityData.length - 1)) * 100}% ${100 - ((val - 90) / 10) * 100}%`).join(' ')"
                    fill="none"
                    stroke="#4ecdc4"
                    stroke-width="2"
                  />
                </svg>
              </div>
              <!-- X轴标签 -->
              <div class="absolute bottom-0 left-0 right-0 flex justify-between text-xs text-blue-300">
                <span v-for="(label, i) in timeLabels" :key="i">{{ label }}</span>
              </div>
            </div>
          </div>
        </div>

        <!-- 设备状态监控 -->
        <div class="bg-blue-800 bg-opacity-50 rounded-lg p-4 transition-all duration-300 hover:shadow-lg hover:bg-opacity-60">
          <h2 class="text-xl font-semibold mb-4 flex items-center">
            <span class="text-yellow-400 mr-2">⚙️</span>
            恒温恒湿一体机/空气净化
          </h2>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="bg-blue-900 bg-opacity-60 p-4 rounded">
              <div class="font-medium mb-2">{{ equipmentStatus.dehumidifier.name }}</div>
              <div class="grid grid-cols-3 gap-2 text-sm">
                <div>除湿: {{ equipmentStatus.dehumidifier.humidity }}%</div>
                <div>制冷: {{ equipmentStatus.dehumidifier.cooling }}%</div>
                <div>制热: {{ equipmentStatus.dehumidifier.heating }}%</div>
              </div>
            </div>
            <div class="bg-blue-900 bg-opacity-60 p-4 rounded">
              <div class="font-medium mb-2">{{ equipmentStatus.airPurifier.name }}</div>
              <div class="text-sm">效率: {{ equipmentStatus.airPurifier.efficiency }}%</div>
            </div>
          </div>
        </div>

        <!-- 3D库房布局 -->
        <div class="bg-blue-800 bg-opacity-50 rounded-lg p-4 transition-all duration-300 hover:shadow-lg hover:bg-opacity-60">
          <h2 class="text-xl font-semibold mb-4 flex items-center">
            <span class="text-yellow-400 mr-2">🏢</span>
            库房布局
          </h2>
          <div class="bg-blue-900 bg-opacity-60 p-6 rounded flex justify-center">
            <div class="relative w-full max-w-2xl h-64 border-2 border-blue-400 rounded-lg">
              <!-- 简化版3D布局 -->
              <div class="absolute top-4 left-4 w-32 h-24 border-2 border-blue-300 rounded bg-blue-800 bg-opacity-50 flex items-center justify-center">
                <span class="text-xs">数字化机房</span>
              </div>
              <div class="absolute top-4 right-4 w-32 h-24 border-2 border-blue-300 rounded bg-blue-800 bg-opacity-50 flex items-center justify-center">
                <span class="text-xs">熏蒸机房</span>
              </div>
              <div class="absolute bottom-4 left-0 right-0 h-24 border-2 border-blue-300 rounded-t bg-blue-800 bg-opacity-50 flex items-center justify-center">
                <span class="text-xs">档案库房</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 右侧设备控制区域 -->
      <div class="col-span-12 lg:col-span-4 space-y-6">
        <!-- 设备控制 -->
        <div class="bg-blue-800 bg-opacity-50 rounded-lg p-4 transition-all duration-300 hover:shadow-lg hover:bg-opacity-60">
          <h2 class="text-xl font-semibold mb-4 flex items-center">
            <span class="text-yellow-400 mr-2">🔌</span>
            设备控制
          </h2>
          <div class="space-y-3">
            <div 
              v-for="device in devices" 
              :key="device.id"
              class="bg-blue-900 bg-opacity-60 p-3 rounded flex justify-between items-center"
            >
              <span>{{ device.name }}</span>
              <button 
                @click="toggleDevice(device)"
                :class="[
                  'px-4 py-1 rounded text-sm font-medium',
                  device.status === '开启' ? 'bg-green-600 hover:bg-green-700' : 'bg-gray-600 hover:bg-gray-700'
                ]"
              >
                {{ device.status }}
              </button>
            </div>
          </div>
        </div>

        <!-- 其他设备状态 -->
        <div class="bg-blue-800 bg-opacity-50 rounded-lg p-4 transition-all duration-300 hover:shadow-lg hover:bg-opacity-60">
          <h2 class="text-xl font-semibold mb-4 flex items-center">
            <span class="text-yellow-400 mr-2">📊</span>
            其他设备
          </h2>
          <div class="space-y-3">
            <div class="bg-blue-900 bg-opacity-60 p-3 rounded flex justify-between items-center">
              <span>恒温恒湿一体机</span>
              <span class="text-green-400">运行中</span>
            </div>
            <div class="bg-blue-900 bg-opacity-60 p-3 rounded flex justify-between items-center">
              <span>空气净化</span>
              <span class="text-green-400">运行中</span>
            </div>
            <div class="bg-blue-900 bg-opacity-60 p-3 rounded flex justify-between items-center">
              <span>空调</span>
              <span class="text-green-400">运行中</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>