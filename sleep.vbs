Dim objShell, lngMinutes, boolValid, strInput
Set objShell = CreateObject("WScript.Shell")

' Prompt user for the duration to keep the system awake
strInput = InputBox("How long do you want to keep your system awake?" & vbCrLf & vbCrLf & "Enter minutes (1 to 1440):", "Awake Duration")

' Check if the user clicked "Cancel" or left the input empty
If strInput = vbEmpty Then
    MsgBox "Operation canceled by the user.", vbOKOnly + vbInformation, "Task Canceled"
Else
    On Error Resume Next
    Err.Clear
    
    ' Convert input to a number and check for errors
    lngMinutes = CLng(strInput)
    boolValid = (Err.Number = 0 And lngMinutes > 0 And lngMinutes <= 1440)
    On Error GoTo 0
    
    If boolValid Then
        ' Input is valid, proceed with the loop to keep the system awake
        For i = 1 To lngMinutes
            WScript.Sleep 40000 ' Wait for 40 seconds
            
            ' Simulate a keypress (Caps Lock) to keep the system awake
            objShell.SendKeys("{CAPSLOCK}") ' Toggle Caps Lock on and off
            WScript.Sleep 100
            objShell.SendKeys("{CAPSLOCK}") ' Toggle it back
        Next
        MsgBox "Forced awake time over. Back to normal routine.", vbOKOnly + vbInformation, "Task Completed"
    Else
        ' Handle invalid input
        MsgBox "Incorrect input. Please enter a numeric value between 1 and 1440.", vbOKOnly + vbCritical, "Task Failed"
    End If
End If

Set objShell = Nothing
WScript.Quit 0
