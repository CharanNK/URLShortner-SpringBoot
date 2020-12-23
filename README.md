# URLShortner-SpringBoot
A spring boot starter project that implements a URL shortner service using MongoDB

Author: Charan N K (charanshetty25595@gmail.com)

# Currently it supports the following features:
* A standalone web server - Implemented using two Hashmap that act as servers. One stores the full URL and other stores the short URL.
* Support HTTP PUT to create a tiny URL for a long URL for both Mongo and Hashmap implementations.
* Support HTTP GET to access the tiny URL, and redirect to the long URL for both Mongo and Hashmap implementations.
* Support HTTP DELETE to delete a URL entry from the system.
* Return appropriate status code if no entry is found.
* URL validation for all the URLs passed.
# Features to be added:
* Support web UI to create tiny URL.
* Support user profile creation and customized URL creation
* Add rate limiter for the tiny URL creation. (do not allow user create too many tiny URLs from a single client IP to avoid attack)
* Distributed system. Multiple server support + consistent hash + load balancer
