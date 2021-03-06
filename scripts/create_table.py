import psycopg2
import time


def connect_and_create(content):
    try:
        connect_str = "dbname='eksidebe' user='eksiuser' host='192.168.1.23' " + \
                      "password='dauut13'"
        # use our connection values to establish a connection
        conn = psycopg2.connect(connect_str)
        # create a psycopg2 cursor that can execute queries
        cursor = conn.cursor()

        for i in content:
            # st = """CREATE TABLE d_""" + parse_date(i) + """ AS SELECT entry_id, entry_date, entry_header, entry_url from debe_entries WHERE entry_date = '""" + i + """';"""
            time.sleep(1)
            cursor.execute(
                """CREATE TABLE d_%s AS SELECT entry_id, entry_date, entry_header, entry_url from debe_entries WHERE 
                entry_date = %s;""",
                (int(parse_date(i)), i))

        # close secure connections
        cursor.close()
        conn.close()
    except Exception as e:
        # print("Uh oh, can't connect. Invalid dbname, user or password?")
        print(e)


def parse_date(date):
    date = date.replace("-", "")
    date = int(date)
    return date


def read_dates_from_file(filename):
    with open(filename) as f:
        content = f.readlines()
    content = [x.strip() for x in content]  # delete \n
    connect_and_create(content)
    # parse_date(content[1])


'''
create table d20200818 as select entry_id, entry_date, entry_header, entry_url
from debe_entries
where entry_date='2020-08-18';
'''

read_dates_from_file('dates.txt')

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
