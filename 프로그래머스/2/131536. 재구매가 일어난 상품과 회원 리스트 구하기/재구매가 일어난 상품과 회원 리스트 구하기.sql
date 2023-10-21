SELECT USER_ID, PRODUCT_ID
FROM ONLINE_SALE
GROUP BY USER_ID, PRODUCT_ID
HAVING COUNT(SALES_DATE) > 1
ORDER BY USER_ID, PRODUCT_ID DESC;

# 회원ID, 상품ID, 동일한 날짜 조합이 여러개라면?
# 지금은 COUNT(SALES_DATE)가 구매한 날짜의 수를 의미하지만
# 그때는 해당 상품을 구매한 횟수는 많지만 구매한 날짜의 수는 아니게 됨.