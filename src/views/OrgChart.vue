<script setup>
import { onMounted, ref } from 'vue'
import * as d3 from 'd3'

const container = ref(null)

// 模拟组织数据
const orgData = {
  name: "公司",
  children: [
    { name: "技术部", children: [
      { name: "前端组", value: 5 },
      { name: "后端组", value: 8 }
    ]},
    { name: "人事部", value: 3 },
    { name: "销售部", children: [
      { name: "华东区", value: 6 },
      { name: "华南区", value: 4 }
    ]}
  ]
}

onMounted(() => {
  const width = 800
  const height = 600

  const svg = d3.select(container.value)
    .append("svg")
    .attr("width", width)
    .attr("height", height)

  const root = d3.hierarchy(orgData)
  const treeLayout = d3.tree().size([height - 100, width - 200])
  treeLayout(root)

  // 连线
  svg.selectAll(".link")
    .data(root.links())
    .enter().append("path")
    .attr("class", "link")
    .attr("d", d3.linkHorizontal()
      .x(d => d.y)
      .y(d => d.x))

  // 节点
  const node = svg.selectAll(".node")
    .data(root.descendants())
    .enter().append("g")
    .attr("class", "node")
    .attr("transform", d => `translate(${d.y},${d.x})`)

  node.append("circle")
    .attr("r", 12)
    .attr("fill", "#4F46E5")

  node.append("text")
    .attr("dy", 18)
    .attr("text-anchor", "middle")
    .text(d => d.data.name)
    .attr("fill", "#333")
    .style("font-size", "14px")
})
</script>

<template>
  <div class="p-6">
    <h1 class="text-xl font-bold mb-4">组织架构图</h1>
    <div ref="container" class="bg-white p-4 rounded-lg shadow"></div>
  </div>
</template>

<style>
.link {
  fill: none;
  stroke: #ccc;
  stroke-width: 2px;
}
</style>