using LinearAlgebra
using FundamentalsNumericalComputation

function question8(n, T, b)
    #Invent gravity
    g = -9.8

    #Construct A: Tridiagonal values and matrix
    d = [2 * n * T for i = 1:n]
    dₜ = [-n * T for i = 1:n-1]
    A = Tridiagonal(dₜ, d, dₜ)

    #Construct f
    if (b == true)
        m = (1 / (10 * n))
        f = [m * g for i = 1:n]
    else
        m = (1 / (5 * (n^2)))
        f = [m * i * g for i = 1:n]
    end


    #Find displacement then 2-norm

    q = A \ f
    tn = norm(q)

    #Fulfill question requirements
    #println("Displaying displacements: ")
    #display(q)
    println("Displaying 2-norm when n = ", n)
    display(tn)
    insert!(q,1,0)
    push!(q,0)
    scatter(q)
end

T = 10
println("Answer to part B")
n = 4
question8(n, T, true)
n = 40
question8(n, T, true)

println("\nAnswer to part C")
n = 4
question8(n, T, false)
n = 40
question8(n, T, false)



