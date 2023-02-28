// There is probably a better way to import the images... :)
import image1 from "../assets/img/books/1.jpg";
import image2 from "../assets/img/books/2.jpg";
import image3 from "../assets/img/books/3.jpg";
import image4 from "../assets/img/books/4.jpg";
import image5 from "../assets/img/books/5.jpg";
import image6 from "../assets/img/books/6.jpg";
const images = [image1, image2, image3, image4, image5, image6];

/**
 * A card for a book
 * @param book Book data to show in the card
 * @return {JSX.Element}
 * @constructor
 */
export function BookCard({ book }) {
  return (
    <div className="card">
      <div className="book-image-container">
        <img src={images[book.id - 1]} alt="A book cover" />
      </div>
      <p>{book.title}</p>
    </div>
  );
}
