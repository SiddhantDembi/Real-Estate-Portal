from tkinter import *
from tkinter import filedialog, messagebox
import mysql.connector

mydb = mysql.connector.connect(host="localhost",
                                user="root",
                                passwd="Root@123",
                                database="real_estate")
mycursor = mydb.cursor()

def savedata():
    fn = filedialog.askopenfilename(title="Select File", filetypes=(("Image File", "*.jpg"), ("All Files", "*")))
    if not fn:
        return
    
    with open(fn, "rb") as f:
        data = f.read()  # This is our binary data
    
    sql = "UPDATE property SET image = %s WHERE property_id = 'P00006'"  # Assuming 'id' is the primary key column
    mycursor.execute(sql, (data, ))
    mydb.commit()
    
    messagebox.showinfo("Success!", "Your file has been saved")

savedata()
