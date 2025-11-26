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
    proxy: {
      '/web': {
        target: 'http://m2ddns.pvtool.com:8080',
        changeOrigin: true,
        secure: false,
        //rewrite: (path) => path.replace(/^\/web/, ''),
      },
      '/com.pvtool.erp.client.kernel': {
        target: 'http://m2ddns.pvtool.com:8080',
        changeOrigin: true,
        secure: false,
        //rewrite: (path) => path.replace(/^\/com.pvtool.erp.client.kernel/, ''),
      },
    }
  }
})