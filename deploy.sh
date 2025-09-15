#!/bin/bash

# Script ayarları
IMAGE_NAME=tsprojectv2
CONTAINER_NAME=tsprojectv2_container

echo "----------------------------"
echo "🛠  Maven Build Başlatılıyor"
echo "----------------------------"

mvn clean package -DskipTests

if [ $? -ne 0 ]; then
  echo "❌ Maven build başarısız oldu!"
  exit 1
fi

echo "✅ Maven build tamamlandı."

echo "----------------------------"
echo "🐳 Docker Image Oluşturuluyor"
echo "----------------------------"

docker build -t $IMAGE_NAME .

if [ $? -ne 0 ]; then
  echo "❌ Docker build başarısız oldu!"
  exit 1
fi

echo "✅ Docker image oluşturuldu: $IMAGE_NAME"

echo "----------------------------"
echo "🧹 Eski Container Kaldırılıyor"
echo "----------------------------"

docker stop $CONTAINER_NAME 2>/dev/null
docker rm $CONTAINER_NAME 2>/dev/null

echo "✅ Eski container kaldırıldı (varsa)."

echo "----------------------------"
echo "🚀 Yeni Container Başlatılıyor"
echo "----------------------------"

docker run -it \
  --name $CONTAINER_NAME \
  -p 8080:8080 \
  $IMAGE_NAME

if [ $? -ne 0 ]; then
  echo "❌ Docker container başlatılamadı!"
  exit 1
fi

echo "✅ Uygulama container olarak ayağa kalktı!"
echo "🌐 http://localhost:8080/swagger-ui/index.html"
