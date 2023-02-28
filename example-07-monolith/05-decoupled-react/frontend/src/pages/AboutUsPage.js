import { useEffect, useState } from "react";
import { requestApiData } from "../tools/apiRequests";
import { GoogleMap } from "../components/GoogleMap";

/**
 * Content for the "About us" page
 * @return {JSX.Element}
 * @constructor
 */
export function AboutUsPage() {
  const [bookCount, setBookCount] = useState("🤔");
  const [authorCount, setAuthorCount] = useState("🤔");

  useEffect(loadStatistics, []);

  function loadStatistics() {
    requestApiData("/statistics", function (statistics) {
      setBookCount(statistics.bookCount);
      setAuthorCount(statistics.authorCount);
    });
  }

  return (
    <article id="about-page">
      <section>
        <h1>About us</h1>
        <p>
          We are the largest library with {bookCount} books from {authorCount}{" "}
          authors in the collection.
        </p>
      </section>
      <section>
        <h2>Find us</h2>
        <GoogleMap />
      </section>
    </article>
  );
}
