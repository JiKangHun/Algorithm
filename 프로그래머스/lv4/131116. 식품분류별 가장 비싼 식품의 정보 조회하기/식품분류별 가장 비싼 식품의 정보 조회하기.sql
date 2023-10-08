SELECT FP.CATEGORY, FP.PRICE, FP.PRODUCT_NAME
FROM FOOD_PRODUCT FP, (SELECT CATEGORY, MAX(PRICE) PRICE
    FROM FOOD_PRODUCT
    GROUP BY CATEGORY
    HAVING CATEGORY IN ("과자", "국", "김치", "식용유")) A
WHERE FP.CATEGORY = A.CATEGORY
AND FP.PRICE = A.PRICE
ORDER BY FP.PRICE DESC;
    