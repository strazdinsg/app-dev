from flask import Flask
from book_service import bookService
from flask_cors import CORS, cross_origin

app = Flask(__name__)

# Allow CORS
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'


@app.route('/books')
@cross_origin()
def books():
    return bookService.get_all_books()


@app.route('/books/favorite')
def favorite():
    return bookService.get_favorites()


@app.route("/statistics")
@cross_origin()
def statistics():
    return {"bookCount": 6, "authorCount": 7}
