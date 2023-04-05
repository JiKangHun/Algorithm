select b.USER_ID, b.NICKNAME, a.price
from
(select writer_id, sum(price) price
from USED_GOODS_BOARD
where status = "DONE"
group by writer_id
having sum(price) >= 700000 ) a, USED_GOODS_USER b
where a.writer_id = b.user_id
order by a.price;
