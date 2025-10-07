import { createRouter, createWebHistory } from 'vue-router'
import { useHRStore } from '../stores/useHRStore'

const routes = [
  { path: '/login', component: () => import('../views/AuthLogin.vue'), meta: { public: true } },
  { path: '/', component: () => import('../views/Dashboard.vue') },
  { path: '/employees', component: () => import('../views/Employees.vue') },
  { path: '/org-chart', component: () => import('../views/OrgChart.vue') },
  { path: '/attendance', component: () => import('../views/Attendance.vue') },
  { path: '/onboarding', component: () => import('../views/Onboarding.vue') },
  { path: '/contracts', component: () => import('../views/Contracts.vue') },
  { path: '/approvals', component: () => import('../views/Approvals.vue') }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const store = useHRStore()
  if (!to.meta.public && !store.token) {
    next('/login')
  } else {
    next()
  }
})

export default router