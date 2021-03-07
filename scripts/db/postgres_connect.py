import psycopg2
import configparser


def connect_db():
    cursor = None  # initial
    conn = None  # initial

    try:
        section_name = 'postgresql'  # section name in the config.ini, it can be used as parameter
        config = configparser.ConfigParser()
        config.read('config.ini')
        if config.has_section(section_name):

            # read the options of the section. the config_params is a list object.
            config_params = config.items(section_name)

            # so we need below code to convert the list object to a python dictionary object.
            # define an empty dictionary.
            db_conn_dict = {}

            # loop in the list.
            for config_param in config_params:
                # get options key and value.
                key = config_param[0]
                value = config_param[1]

                # add the key value pair in the dictionary object.
                db_conn_dict[key] = value

            # get connection object use above dictionary object.
            conn = psycopg2.connect(**db_conn_dict)
            print("******* get postgresql database connection with configuration file ********", "\n")
        # connect_str = "dbname='eksidebe' user='eksiuser' host='192.168.1.23' " + \
        #               "password='dauut13'"
        # create a psycopg2 cursor that can execute queries

        cursor = conn.cursor()
    except Exception as e:
        print(e)

    return cursor, conn
