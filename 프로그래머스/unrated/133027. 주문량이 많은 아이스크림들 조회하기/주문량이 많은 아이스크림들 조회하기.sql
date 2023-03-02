select j.flavor
from
(select flavor, sum(total_order) total_oder
from july
group by flavor) j, first_half f
where j.flavor = f.flavor
order by j.total_oder + f.total_order desc
limit 3;