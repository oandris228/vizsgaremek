export function ProductFelvetel() {
    return <>
        <h1>Felvétel</h1>
        <form method="post" action="http://localhost:3000/products">
            <label>ID:</label>
            <input type="number" name="id" /><br />

            <label>Name:</label>
            <input type="text" name="name" /><br />

            <label>Price:</label>
            <input type="number" name="price" /><br />

            <label>Category:</label>
            <input type="text" name="category" /><br />

            <label>Tea ID:</label>
            <input type="number" name="tea_id" /><br />

            <label>Other ID:</label>
            <input type="number" name="other_id" /><br />

            <label>Tea Type:</label>
            <input type="text" name="tea_type" /><br />

            <label>Tea Flavor:</label>
            <input type="text" name="tea_flavor" /><br />

            <label>Other Description:</label>
            <input type="text" name="others_description" /><br />

            <label>Other Image:</label>
            <input type="text" name="others_img" /><br />

            <button type="submit">Submit</button>
        </form>
    </>
}