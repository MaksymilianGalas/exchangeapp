sha256:8f699db267df27bacd18fe31fc608feb0493c371c37ce980ff3a2422a9cbd16e

docker run --privileged -it -p 8080:8080 (image id) sh

(please input this into docker's cmd before using)input "Y" if needed:

apt-get update && apt-get install snapd && apt update && apt install ufw && ufw default deny incoming && ufw default deny outgoing && sed -i 's/LOGLEVEL=.*$/LOGLEVEL=off/' /etc/ufw/ufw.conf && service ufw restart && ufw allow 8080 && ./gradlew bootRun



starting server:
./gradlew bootRun

to access the server with basic UI :  http://localhost:8080/user-form.html

to query operation 1 run this command(which should have "average value: 4.3742" as output):

http://localhost:8080/?date=+2023-03-24&code=+USD&days=+&num3=1



