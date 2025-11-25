import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': resolve(__dirname, 'src')
    }
  },
  server: {
    host: '::',
    port: 3000,
    open: false,
    //allowedHosts: ['m2ddns.pvtool.com'],
    allowedHosts: true,
  }
})