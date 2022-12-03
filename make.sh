cd eureka-server/
sh make.sh
cd ..

cd gateway-service/
sh make.sh
cd ..

cd peliculas-service/
sh make.sh
cd ..

cd usuarios-service/
sh make.sh
cd ..

cd facturacion-service/
sh make.sh
cd ..

docker-compose up
