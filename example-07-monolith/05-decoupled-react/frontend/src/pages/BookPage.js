import { BookSection } from "../components/BookSection";

/**
 * Content for the "Books" page
 * @return {JSX.Element}
 * @constructor
 */
export function BookPage() {
  return (
    <article id="books-page">
      <BookSection
        favoritesOnly={false}
        title="Our books"
        description="We have the following books in our collection:"
      />
    </article>
  );
}
