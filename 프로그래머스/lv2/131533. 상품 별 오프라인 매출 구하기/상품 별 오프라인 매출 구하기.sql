SELECT PRODUCT_CODE, SUM(SALES_AMOUNT) * PRICE SALES
FROM PRODUCT LEFT JOIN OFFLINE_SALE
ON PRODUCT.PRODUCT_ID = OFFLINE_SALE.PRODUCT_ID
GROUP BY OFFLINE_SALE.PRODUCT_ID
ORDER BY SALES DESC, PRODUCT_CODE;