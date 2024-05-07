class BookRepository:
    """ Book storage. In real world, this would be a database. """

    @staticmethod
    def get_all_books():
        return books

    @staticmethod
    def get_first(count):
        return books[:count]


# Static book storage
books = [
    {
        "id": 1,
        "title": "Learning Web Design",
        "yearIssued": 2018,
        "numberOfPages": 808,
        "genre": {
            "id": 5,
            "name": "Web Development"
        },
        "authors": [
            {
                "id": 7,
                "firstName": "Jennifer",
                "lastName": "Robbins",
                "birthYear": 1980
            }
        ]
    },
    {
        "id": 2,
        "title": "This is Going to Hurt",
        "yearIssued": 2018,
        "numberOfPages": 288,
        "genre": {
            "id": 4,
            "name": "Autobiography"
        },
        "authors": [
            {
                "id": 6,
                "firstName": "Adam",
                "lastName": "Kay",
                "birthYear": 1980
            }
        ]
    },
    {
        "id": 3,
        "title": "Computer Networking",
        "yearIssued": 2021,
        "numberOfPages": 800,
        "genre": {
            "id": 2,
            "name": "Computer Networks"
        },
        "authors": [
            {
                "id": 4,
                "firstName": "Jim",
                "lastName": "Kurose",
                "birthYear": 1956
            },
            {
                "id": 2,
                "firstName": "Keith",
                "lastName": "Ross",
                "birthYear": 1957
            }
        ]
    },
    {
        "id": 4,
        "title": "12 Rules for Life",
        "yearIssued": 2018,
        "numberOfPages": 409,
        "genre": {
            "id": 3,
            "name": "Medical Applied Psychology"
        },
        "authors": [
            {
                "id": 3,
                "firstName": "Jordan",
                "lastName": "Peterson",
                "birthYear": 1962
            }
        ]
    },
    {
        "id": 5,
        "title": "The Truth About Chuck Norris: 400 Facts About the World's Greatest Human",
        "yearIssued": 2007,
        "numberOfPages": 409,
        "genre": {
            "id": 1,
            "name": "Parody"
        },
        "authors": [
            {
                "id": 5,
                "firstName": "Ian",
                "lastName": "Spector",
                "birthYear": 1988
            }
        ]
    },
    {
        "id": 6,
        "title": "Becoming",
        "yearIssued": 2018,
        "numberOfPages": 448,
        "genre": {
            "id": 4,
            "name": "Autobiography"
        },
        "authors": [
            {
                "id": 1,
                "firstName": "Michele",
                "lastName": "Obama",
                "birthYear": 1964
            }
        ]
    }

]
