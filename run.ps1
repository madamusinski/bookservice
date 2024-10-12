mvn clean package

Set-Location -Path "./book-management-service"
docker build --platform linux/amd64 -t book-management-service .

Set-Location -Path "../book-rental-information-service"
docker build --platform linux/amd64 -t book-rental-information-service .

Set-Location -Path ".."
docker compose up