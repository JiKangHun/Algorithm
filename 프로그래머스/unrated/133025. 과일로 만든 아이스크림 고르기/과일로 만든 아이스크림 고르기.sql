select f.flavor
from FIRST_HALF f, ICECREAM_INFO i
where f.flavor = i.flavor
and i.INGREDIENT_TYPE = "fruit_based"
and f.total_order >= 3000
order by f.total_order desc;