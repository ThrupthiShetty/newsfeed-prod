##docker build . -f Dockerfile.prod -t shettyjm/ss_ac_service
FROM nginx:1.14.1-alpine

##Copy dist folder 
RUN mkdir /newsfeed-dist
##WORKDIR /newsfeed-dist

#COPY dist /newsfeed-dist




## Copy our default nginx config
RUN ls -ltr /newsfeed-dist

COPY nginx.conf /etc/nginx/conf.d/

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

## From ‘builder’ stage copy over the artifacts in dist folder to default nginx public folder

#COPY dist/ /dist/



COPY dist/newsfeed /usr/share/nginx/html

RUN echo "---  /newsfeed-dist/newsfeed contents ---"
##RUN ls -ltr /newsfeed-dist/shotstories

##RUN mv  /newsfeed-dist/shotstories /usr/share/nginx/html

RUN ls -ltr /usr/share/nginx/html

RUN rm -r  /newsfeed-dist

RUN echo "--- starting nginx ---"

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
