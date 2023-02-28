import { useEffect, useState } from "react";
import { requestApiData } from "../tools/apiRequests";
import { BookCard } from "./BookCard";

/**
 * A page section showing books.
 * @param {boolean} favoritesOnly When true, query only favorite books, when false - all books
 * @param {string} title The title of the section
 * @param {string} description A descriptive text to show in the section
 * @return {JSX.Element}
 * @constructor
 */
export function BookSection({ favoritesOnly, description, title }) {
  const [books, setBooks] = useState([]);
  useEffect(loadBooksFromApi, [favoritesOnly]);

  function loadBooksFromApi() {
    const apiUrl = favoritesOnly ? "/books/favorite" : "/books";
    requestApiData(apiUrl, function (newBooks) {
      setBooks(newBooks);
    });
  }

  const descriptionParagraph = description ? <p>{description}</p> : <></>;

  let content = <p>Loading...</p>;
  if (books.length > 0) {
    content = books.map((book, index) => <BookCard key={index} book={book} />);
  }

  return (
    <section>
      <h2>{title}</h2>
      {descriptionParagraph}
      <div className="book-container">{content}</div>
    </section>
  );
}
