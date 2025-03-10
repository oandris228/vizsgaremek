export function Regisztracio() {
    return <>
        <h1>Felvétel</h1>
        <form method="post" action="http://localhost:3000/users">
            <label>Username:</label>
            <input type="text" name="username" /><br />

            <label>email:</label>
            <input type="text" name="email" /><br />

            <label>Password:</label>
            <input type="password" name="password" /><br />

            <label>Shipping Address (Optional):</label>
            <input type="num" name="tea_id" /><br />

            <button type="submit">Submit</button>
        </form>
    </>
}