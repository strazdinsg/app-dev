import { Link } from "react-router-dom";

/**
 * The navigation of the page.
 * @return {JSX.Element}
 * @constructor
 */
export function Navigation() {
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/books">Books</Link>
        </li>
        <li>
          <Link to="/about">About us</Link>
        </li>
      </ul>
    </nav>
  );
}
