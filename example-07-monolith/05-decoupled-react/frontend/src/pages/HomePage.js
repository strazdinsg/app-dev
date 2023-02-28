import { BookSection } from "../components/BookSection";

/**
 * Content for the "Home" page
 * @return {JSX.Element}
 * @constructor
 */
export function HomePage() {
  return (
    <article id="home-page">
      <section>
        <h1>Welcome to our library!</h1>
        <p>We are the largest library in town with plenty of books.</p>
      </section>

      <BookSection
        favoritesOnly={true}
        title="Books of the week"
        description=""
      />
    </article>
  );
}
