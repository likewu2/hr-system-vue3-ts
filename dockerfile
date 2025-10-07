# Dockerfile
FROM nginx:alpine
COPY dist /usr/share/nginx/html/hr-system
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80