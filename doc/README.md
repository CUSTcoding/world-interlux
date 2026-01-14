# Design Worldinterlux.com

## 1. Requirements:
* 1. Search for product
* 2. Recomendations on Homepages
* 3. Place Order
* 4. Check Order status
* 5. Write/View product reviews

## 2 Assumptions:
* 1. User Profile Creations is provided
* 2. Product Onbording is provided
* 3. Payment Service is proviided

## Non-Fuctional Requirements:
* 1. Low Latency (Recomendations & Search)
* 2. Hig consistency (Placing order, order status and payments)
Capacity Estimations:
Active Users: 1000 Monthly active users
                each user search for 20 product for monthly
                = 1000 * 20 seach/monthly
                = 20.000 Search /(30 days * 24 hours * 60 mins * 60 secunds)

Total Products: 2000
Assume: 1 Product require 10MB storege (images + descriptions)
Total product storage: 2000 * 10MB
                    = 2*10^3 * 10^6
                    = 20*10^9
                    = 20.000000000 Storage required to all product 