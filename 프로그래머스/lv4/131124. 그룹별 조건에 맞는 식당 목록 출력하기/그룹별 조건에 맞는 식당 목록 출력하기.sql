select member_name, review_text, DATE_FORMAT(review_date, "%Y-%m-%d") review_date
from member_profile m, rest_review r
where m.member_id = r.member_id
and r.member_id in (select member_id
                    from rest_review
                    group by member_id
                    having count(member_id) = (select max(count)
                                                 from 
                                                (select count(member_id) count
                                                    from rest_review
                                                    group by member_id) f) )
order by review_date, review_text;
