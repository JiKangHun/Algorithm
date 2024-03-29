SELECT SUBSTR(SALES_DATE,1,10) SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
FROM ONLINE_SALE
WHERE DATE_FORMAT(SALES_DATE, "%y:%m") = "22:03"
UNION
SELECT SUBSTR(SALES_DATE,1,10) SALES_DATE, PRODUCT_ID, NULL USER_ID, SALES_AMOUNT
FROM OFFLINE_SALE
WHERE DATE_FORMAT(SALES_DATE, "%y:%m") = "22:03"
ORDER BY SALES_DATE, PRODUCT_ID, USER_ID;