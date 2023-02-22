select month(start_date) month, car_id, count(car_id) records
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where DATE_FORMAT(start_date, "%y-%m") >= "22-08"
and DATE_FORMAT(start_date, "%y-%m") < "22-11"
and car_id
            in 
                (select car_id
                from CAR_RENTAL_COMPANY_RENTAL_HISTORY
                where DATE_FORMAT(start_date, "%y-%m") >= "22-08"
                and DATE_FORMAT(start_date, "%y-%m") < "22-11"
                group by car_id
                having count(car_id) >= 5)
group by month(start_date), car_id
having count(car_id) != 0
order by month(start_date), car_id desc;

