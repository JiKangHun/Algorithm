select FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from rest_info
where favorites in
                ( select max(favorites)
                    from REST_INFO 
                    group by food_type )
group by food_type
order by food_type desc;
