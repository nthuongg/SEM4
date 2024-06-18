import React, { useState, useEffect } from 'react';
import axios from 'axios';

const BookList = () => {
  const [books, setBooks] = useState([]);
  const [newBookName, setNewBookName] = useState('');
  const [selectedBookId, setSelectedBookId] = useState(null);
  const [selectedBookName, setSelectedBookName] = useState('');
  const [error, setError] = useState(null);
  const [addError, setAddError] = useState('');

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const res = await axios.get('http://localhost:8080/api/v1/books');
      const books = res.data.content; 
      setBooks(books);
    } catch (error) {
      setError(error.message);
    }
  };

  const handleAddBook = async () => {
    if (newBookName.trim() === '') {
      setAddError('Book name is required.');
      return;
    }
    
    try {
      await axios.post('http://localhost:8080/api/v1/books', { name: newBookName, library: { id: 1 } });
      setNewBookName('');
      setAddError('');
      fetchBooks();
    } catch (error) {
      setError(error.message);
    }
  };

  const handleUpdateBook = async () => {
    try {
      await axios.put(`http://localhost:8080/api/v1/books/${selectedBookId}`, { name: selectedBookName, library: { id: 1 } });
      setSelectedBookId(null);
      setSelectedBookName('');
      fetchBooks();
    } catch (error) {
      setError(error.message);
    }
  };

  const handleDeleteBook = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/books/${id}`);
      fetchBooks();
    } catch (error) {
      setError(error.message);
    }
  };

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div>
      <h1>Book List</h1>
      <ul>
        {books.map(book => (
          <li key={book.id}>
            {book.name}
            <button onClick={() => {
              setSelectedBookId(book.id);
              setSelectedBookName(book.name);
            }}>
              Edit
            </button>
            <button onClick={() => handleDeleteBook(book.id)}>Delete</button>
          </li>
        ))}
      </ul>

      <h2>Add New Book</h2>
      <input
        type="text"
        value={newBookName}
        onChange={(e) => setNewBookName(e.target.value)}
      />
      <button onClick={handleAddBook}>Add</button>
      {addError && <p style={{ color: 'red' }}>{addError}</p>}

      {selectedBookId && (
        <div>
          <h2>Edit Book</h2>
          <input
            type="text"
            value={selectedBookName}
            onChange={(e) => setSelectedBookName(e.target.value)}
          />
          <button onClick={handleUpdateBook}>Update</button>
        </div>
      )}
    </div>
  );
};

export default BookList;
