build:
	docker build -t hotels-search-service -f Dockerfile .

run:
	docker run -it -p8080:8080 -espring.profiles.active=prod hotels-search-service

