from db import postgres_connect as pc


# finds empty tables
def find_unmatched_table(date, cursor):
    entry_count = ret_entry_count(date, cursor)
    actual_entry_count = ret_actual_count(date, cursor)
    if entry_count != actual_entry_count:
        print("date = " + date + "; entry_count = " + str(entry_count) + "; actual_count = " + str(actual_entry_count))
        return False
    else:
        return True


def ret_entry_count(date, cursor):
    cursor.execute("SELECT COUNT(*) FROM debe_entries WHERE entry_date='" + date + "';")
    count = cursor.fetchall()
    return count[0]


def ret_actual_count(date, cursor):
    cursor.execute("SELECT entry_count FROM debe_list WHERE list_date='" + date + "';")
    count = cursor.fetchall()
    return count[0]


# prints empty dates
def print_anomalies(date):
    with open("incomplete_dates.txt", "a") as file:
        file.write(date + "\n")


def read_dates_from_file(filename):
    with open(filename) as f:
        content = f.readlines()
    content = [x.strip() for x in content]  # delete \n
    return content


def main():
    dates = read_dates_from_file("dates.txt")
    cursor, conn = pc.connect()  # connect via credentials, config.ini requires absolute path
    i = 0
    for d in dates:
        res = find_unmatched_table(d, cursor)
        if not res:
            i += 1
            print_anomalies(d)
    print(str(i) + " anomalies found")
    cursor.close()
    conn.close()


if __name__ == "__main__":
    main()
