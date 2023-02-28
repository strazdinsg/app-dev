import { Route, Routes } from "react-router-dom";
import { HomePage } from "../pages/HomePage";
import { BookPage } from "../pages/BookPage";
import { AboutUsPage } from "../pages/AboutUsPage";

/**
 * The main container which selects the content based on the active URL (selected menu item).
 * @return {JSX.Element}
 * @constructor
 */
export function MainContainer() {
  return (
    <main id="main-content">
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/books" element={<BookPage />} />
        <Route path="/about" element={<AboutUsPage />} />
      </Routes>
    </main>
  );
}
