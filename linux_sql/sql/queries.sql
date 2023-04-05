
--Insert Data into the facilities table, facid: 9, Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.
Insert into cd.facilities(
  facid, name, membercost, guestcost, 
  initialoutlay, monthlymaintenance
) 
values 
  (9, 'Spa', 20, 30, 100000, 800);

--Insert calculated data into facilities table, Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.
Insert into cd.facilities(
    facid, name, membercost, guestcost,
    initialoutlay, monthlymaintenance
)
values
    (
            (
                select
                    max(facid)
                from
                    cd.facilities
            )+ 1,
            'Spa',
            20,
            30,
            100000,
            800
    );
--alter the price of the second tennis court so that it costs 10% more than the first one
update 
  cd.facilities 
set 
  initialoutlay = 10000 
where 
  initialoutlay = 8000;
update 
  cd.facilities 
set 
  membercost =(membercost * 1.1), 
  guestcost =(guestcost * 1.1) 
where 
  facid = 1;

--delete all bookings from the cd.bookings table.
delete from
    cd.bookings;
--remove member 37, who has never made a booking, from our database.
delete from
    cd.members
where
        memid = 37;

--Basic statements
-- produce a list of facilities that charge a fee to members, and that fee is less than 1/50th of the monthly maintenance cost
select 
  facid, 
  name, 
  membercost, 
  monthlymaintenance 
from 
  cd.facilities 
where 
  membercost > 0 
  and (
    membercost < monthlymaintenance / 50.0
  );

--produce a list of all facilities with the word 'Tennis' in their name
Select
    *
from
    cd.facilities
where
        name like '%Tennis%';
--Retrieve the details of facilities with ID 1 and 5
select
    *
from
    cd.facilities
where
        facid in(1, 5);
-- Produce a list of members who joined after the start of September 2012
select
    memid,
    surname,
    firstname,
    joindate
from
    cd.members
where
        joindate >= DATE ('2012-09-01');
-- Provide a combined list of all surnames and all facility names. 
select
    surname
from
    cd.members
union
select
    name
from
    cd.facilities;
--join statements
--1st statement
select
    starttime
from
    cd.bookings
        INNER JOIN cd.members ON cd.bookings.memid = cd.members.memid
where
        cd.members.firstname = 'David'
  and cd.members.surname = 'Farrell';
--2nd statement
select
    cd.bookings.starttime as start,
    cd.facilities.name as fname
from
    cd.bookings
        INNER JOIN cd.facilities ON cd.bookings.facid = cd.facilities.facid
where
        cd.bookings.starttime >= '2012-09-21'
  and cd.bookings.starttime < '2012-09-22'
  and cd.facilities.name in(
                            'Tennis Court 1', 'Tennis Court 2'
    )
order by
    cd.bookings.starttime;
--3rd
select
    mem.firstname as memfname,
    mem.surname as memsname,
    ref.firstname as recfname,
    ref.surname as recsname
from
    cd.members mem
        left outer Join cd.members ref ON ref.memid = mem.recommendedby
order by
    memsname,
    memfname;
--4th
select
    DISTINCT ref.firstname,
             ref.surname
from
    cd.members mem
        inner join cd.members ref ON ref.memid = mem.recommendedby
order by
    ref.surname,
    ref.firstname;
--5th
select
    distinct mem.firstname || ' ' || mem.surname as member,
             (
                 select
                         ref.firstname || ' ' || ref.surname as recommender
                 from
                     cd.members ref
                 where
                         ref.memid = mem.recommendedby
             )
from
    cd.members mem
order by
    member;
--aggregation
--1st
select
    recommendedby,
    COUNT(*)
from
    cd.members
where
    recommendedby is not null
group by
    (recommendedby)
order by
    recommendedby;
--2nd
select
    facid,
    sum(slots) as "Total slots"
from
    cd.bookings
where
    facid is not null
group by
    (facid)
order by
    facid;
--3rd
select
    facid,
    sum(slots) as "Total Slots"
from
    cd.bookings
where
        starttime >= '2012-09-01'
  and starttime <= '2012-10-01'
group by
    (facid)
order by
    sum(slots);
--4th
select
    facid,
    EXTRACT(
            Month
            from
            starttime
        ) as month,
  sum(slots) as "Total slots"
from
    cd.bookings
where
    EXTRACT(
    Year
    from
    starttime
    )= 2012
group by
    facid,
    month
order by
    facid,
    month;
--5th
select
    count(distinct memid)
from
    cd.bookings;
--6th
select
    mem.surname,
    mem.firstname,
    mem.memid,
    min(b.starttime) as starttime
from
    cd.bookings b
        inner join cd.members mem ON b.memid = mem.memid
where
        starttime >= '2012-09-01'
group by
    mem.surname,
    mem.firstname,
    mem.memid
order by
    memid;
--7th
Select
    (
        select
            count(*)
        from
            cd.members
    ),
    firstname,
    surname
from
    cd.members
order by
    joindate;
--8th
select row_number() over(), firstname, surname
from cd.members
order by joindate
--9th
select facid, total from (
                             select facid, sum(slots) total, rank() over (order by sum(slots) desc) rank
                             from cd.bookings
                             group by facid
                         ) as ranked
where rank = 1

--string related statements
--1st
select
        surname || ',' || ' ' || firstname as name
from
    cd.members;

--2nd
select
    memid,
    telephone
from
    cd.members
where
    telephone ~ '[()]'
order by
    memid;

--3rd
select
    substring(surname, 1, 1) as letter,
    count(*)
from
    cd.members
group by
    (
        substring(surname, 1, 1)
        )
order by
    letter;
